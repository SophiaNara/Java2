package wombatdb;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Scanner;

public class WombatDB {
    private final Scanner scan = new Scanner(System.in);
    private final ArrayList<String> tableIndex = new ArrayList<>();
    private final ArrayList<DBComponent> tableData = new ArrayList<>();

    private int commandNumber = 1;

    public void commandLoop() {
        System.out.println("Starting command loop");
        String prompt;
        ArrayList<String> input;
        do {
            System.out.print(commandNumber++ + "? ");
            prompt = scan.nextLine().trim();
            input = splitLine(prompt);
            if (input.isEmpty()) {
                System.out.println("No commands on line");
                continue;
            }
            String command = input.get(0);
            System.out.println("Command = " + command);
            for (int i = 1; i < input.size(); i++) {
                System.out.println("Parameter " + i + " = " + input.get(i));
            }
            try {
                process(input);
            } catch (NumberFormatException nfe) {
                System.out.println("Expecting int, found " +
                        nfe.getMessage().replaceFirst(".*For input string: ", ""));
            } catch (InvalidArgList | InvalidTableName | InvalidClassName ival) {
                System.out.println(ival);
            } catch (NoClassDefFoundError ncdfe) {
                System.out.println(ncdfe.getMessage().replaceFirst(".*(wrong name: practical6/wombat) ", ""));
            } catch (NoColumnException nc) {
                System.out.println(nc.getMessage().replaceFirst(".*practical6.NoColumn: ", ""));
            }
        } while (!prompt.equalsIgnoreCase("quit"));


    }

    private ArrayList<String> splitLine(String input) {
        ArrayList<String> words = new ArrayList<>();
        int i = 0;
        Scanner lineScanner = new Scanner(input);

        while (lineScanner.hasNext()) {
            if (i++ == 0) {
                words.add(lineScanner.next().toLowerCase().trim());
            } else {
                words.add(lineScanner.next().trim());
            }
        }
        return words;
    }

    private void process(ArrayList<String> input) {
        switch (input.get(0)) {
            case "quit" -> checkArgs(input, 1, 1);
            case "addtable" -> do_addtable(input);
            case "insert" -> do_insert(input);
            case "printdb" -> do_printdb(input);
            case "select" -> do_select(input);
            case "describe" -> do_desctable(input);
            default -> System.out.println("Invalid command " + input.get(0));
        }
    }

    private void do_desctable(ArrayList<String> input) {
        checkArgs(input, 2, 2);
        String s = input.get(1);
        if (!tableExists(s)) {
            throw new InvalidTableName(s);
        }
        try {
            Class<?> tbl = Class.forName(this.getClass().getPackageName() + "." + s);
            Constructor[] constructor = tbl.getConstructors();
            DBComponent dbc = (DBComponent) constructor[0].newInstance();
            System.out.println(dbc.getStructure());
        } catch (Exception e) {
            throw new InvalidTableName(s);
        }
    }

    private void do_addtable(ArrayList<String> input) {
        checkArgs(input, 2, 2);
        tableIndex.add(input.get(1));
    }

    private void do_insert(ArrayList<String> input) {
        String s = input.get(1);
        if (!tableExists(s)) {
            throw new InvalidTableName(s);
        }
        checkArgs(input, 4, 100);
        String[] args = new String[input.size() - 2];
        String[] inputarray = input.toArray(new String[0]);
        try {
            Class<?> obj = Class.forName(this.getClass().getPackageName() + "." + s);
            Constructor[] ctor = obj.getConstructors();
            System.arraycopy(inputarray, 2, args, 0, input.size() - 2);
            tableData.add((DBComponent) ctor[1].newInstance(args));
        } catch (Exception e) {
            throw new InvalidArgList(input.get(0));
        }
    }


    private DBComponent getClassInstance(String tablename) {
        DBComponent dbc;
        try {
            Class<?> obj = Class.forName(this.getClass().getPackageName() + "." + tablename);
            Constructor[] constructor = obj.getConstructors();
            dbc = (DBComponent) constructor[0].newInstance();
            return dbc;
        } catch (Exception e) {
            throw new InvalidTableName(tablename);
        }
    }

    private void fillColArray(int[] cols, ArrayList<String> input, boolean star) {
        if (star) {
            for (int i = 0; i < cols.length; i++) {
                cols[i] = i + 1;
            }
        } else {
            try {
                for (int i = 0; i < cols.length; i++)
                    cols[i] = Integer.parseInt(input.get(i + 1));
            } catch (NumberFormatException nfe) {
                throw new InvalidArgList(input.get(0));
            }
        }

    }


    private void do_select(ArrayList<String> input) {
        DBComponent dbc;
        ArrayList<DBComponent> tbls = new ArrayList<>();
        checkArgs(input, 4, 15);
        int indexOfFrom = input.indexOf("from");
        String s = input.get(indexOfFrom + 1);
        dbc = getClassInstance(s);
        int numCols = dbc.getNumberOfColumns();

        if (indexOfFrom - 1 > numCols) {
            throw new InvalidArgList(input.get(0));
        }
        boolean star = input.get(1).equals("*");
        int[] cols = star ? new int[numCols] : new int[indexOfFrom - 1];
        fillColArray(cols, input, star);

        try {
            for (DBComponent tbl : tableData)
                if (Class.forName(this.getClass().getPackageName() + "." + s).isInstance(tbl))
                    tbls.add(tbl);
        } catch (Exception e) {
            throw new InvalidClassName(s);
        }
        printColumnNames(tbls.get(0), cols);
        for (DBComponent o : tbls) {
            for (int col : cols) System.out.print(o.getColumn(col) + "\t");
            System.out.println();
        }

    }


    private void printColumnNames(DBComponent dbComponent, int[] cols) {
        System.out.print("| ");
        for (int col : cols) System.out.print(dbComponent.getColumnName(col) + " | ");
        System.out.println();
    }

    private void do_printdb(ArrayList<String> input) {
        checkArgs(input, 2, 2);
        String table = input.get(1);
        if (!tableExists(table))
            throw new InvalidTableName(table);
        try {
            for (DBComponent td : tableData)
                if (Class.forName(this.getClass().getPackageName() + "." + table).isInstance(td))
                    System.out.println(td);
        } catch (Exception e) {
            throw new InvalidClassName(input.get(1));
        }
    }

    private boolean tableExists(String tableName) {
        return tableIndex.contains(tableName);
    }

    private void checkArgs(ArrayList<String> input, int min, int max) {
        if (input.size() < min || input.size() > max) {
            throw new InvalidArgList(input.get(0));
        }
    }
}

