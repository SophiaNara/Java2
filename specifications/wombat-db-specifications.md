## The Task

You should develop your program in stages, with each stage progressively adding features.

Note that each stage is more difficult than the previous stage. You should aim for as high a stage as you can, bearing
in mind the time available, your skills, and other demands on your time.

This assignment exercise has been broken down into smaller stages and so there are 8 tasks.

The idea of the assignment is to develop a simple database.

### Stage 1

Declare a class called `WombatDB` that has a single public method called `commandLoop()` that prints out "**<samp>
Starting command loop</samp>**" followed by a new line.

When the program is run it should print out the single line:

```
Starting command loop
```

Make sure you understand why before proceeding...

### Stage 2

Extend the method commandLoop as follows.

1. Include a loop that:
    1. prints the string "<b><samp>? </samp></b>" (without a newline),
    2. reads in a line of input,
    3. converts the input to lowercase,
    4. prints out a line containing "**<samp>Command = </samp>"** followed by the converted input (with a newline),
   5. if the input is the string "**<samp>quit<samp>**" then the loop should terminate (see below: user input is
      <mark>**highlighted bold**</mark>).<br><br>

   **Hint:** use a loop like

```java
    do{
            // other statements here...
    
    }while(/* termination condition */);
```

<pre><code>Starting command loop 
? <mark><b>Junk</b></mark> 
Command = junk 
? <mark><b>HeLp</b></mark> 
Command = help 
? <mark><b>quit</b></mark> 
Command = quit
</code></pre>

### Stage 3

Modify the `commandLoop` method so that it ignores (throws away) leading and trailing spaces in the input. Try
typing "**<samp>&nbsp;&nbsp; quit</samp>**" (instead of "**<samp>quit</samp>**") to your Stage 2 program - it will (
probably) not recognise the input as the quit command.

Modify the `commandLoop` method further so that it numbers each command (by putting a number before the "<b><samp>
? </samp></b>" prompt).

An example follows (note that user input is shown in <mark>**highlighted bold**</mark>):

<pre><code>Starting command loop 
1? <mark><b>Junk</b></mark> 
Command = junk 
2? <mark><b>HeLp</b></mark> 
Command = help 
3? <mark><b>    quit</b></mark> 
Command = quit
</code></pre>

### Stage 4

Modify the program so that a command can consist of several words on the one line. The *first word is treated as the
command* and the *remaining words as its parameters*.

All spaces should be ignored and all remaining characters converted to lowercase.

The parameters should be printed out, one per line (see example below).  
**Hint:** make use of a `StringTokenizer`.

A line with no command should cause the message "**<samp>No command on line</samp>**" to be output.

An example follows (note that user input is shown in <mark>**highlighted bold**</mark>):

<pre><code>Starting command loop 
1? <mark><b>Junk</b></mark> 
Command = junk 
2? <mark>      <b>HeLp one     Two  </b></mark>
Command = help 
Parameter 1 = one 
Parameter 2 = two 
3? 
No commands on line 
4? <mark>    <b>quit</b></mark> 
Command = quit
</code></pre>

### Stage 5

The remainder of the assignment develops a simple database management application (no knowledge of databases is
required).

Modify the command loop so that only the command (and not the parameters) are converted to lower case.

In this stage actions will be attached to commands. You may assume all commands are correctly formatted (you do not need
to check that the correct number and type of parameters have been entered).

The commands are as follows:

- <samp>**insert Wombat *name length***</samp><br>
  inserts a wombat with the name, *name*, and length, *length* into a list (table) of wombats. The first
  parameter (<samp>Wombat</samp>) can be ignored for this stage.

- <samp>**printdb Wombat**</samp><br>
  prints the list (table) of wombats (see example below).

An example follows (note that user input is shown in <mark>**highlighted bold**</mark>):
<pre><code>Starting command loop
1?
No commands on line
2? <mark><b>Junk One two</b></mark>
Command = junk
Parameter 1 = One
Parameter 2 = two
Invalid command junk
3? <mark><b>insert Wombat wilbur 45</b></mark>
Command = insert
Parameter 1 = Wombat
Parameter 2 = wilbur
Parameter 3 = 45
4? <mark><b>insert Wombat joice 55</b></mark>
Command = insert
Parameter 1 = Wombat
Parameter 2 = joice
Parameter 3 = 55
5? <mark><b>printdb Wombat</b></mark>
Command = printdb
Parameter 1 = Wombat
Wombat:  name = wilbur, length = 45cm
Wombat:  name = joice, length = 55cm
6? <mark><b>quit</b></mark>
Command = quit
</code></pre>

### <span style="color: red;">The next two stages are Challenging</span>

### Stage 6

Extend the previous stage so that the format of commands is checked for the correct number and type of parameters,
according to the following table:

<table>
<tr><th>Command
    <th>requirement
    <th>error message to be output if requirement not met

<tr><td rowspan=3> <samp>insert</samp> <samp>Wombat</samp> <i>name</i> <i>length</i>
    <td>The first parameter must be <samp>Wombat</samp>
   <td>
  <samp>Invalid table name: ...</samp> (should be of type <samp>RuntimeException</samp>)
<tr><td>
  The <i>length</i> parameter must be an integer
<td>
<samp>Expecting int, found "..."</samp>

<tr><td>Correct number of parameters (3)
  <td><samp>Invalid parameter list: insert</samp>

<tr><td rowspan=2> <samp>printdb</samp> <samp>Wombat</samp>
    <td>The first parameter must be <samp>Wombat</samp>
<td>  <samp>java.lang.RuntimeException: Invalid table name: ...</samp>
<tr><td>Correct number of parameters (1)
  <td><samp>Invalid parameter list: printdb</samp>
<tr>
    <td><samp>quit</samp>
    <td>No parameters
  <td>Invalid parameter list: quit
</table>

Error checking should be done in the following order:

1. correct number of parameters
2. correct table name
3. correct type of parameters

The only exception is that for the "<samp>**insert**</samp>" command, provided it has at least 2 parameters, the table
name should be checked first.

An example follows (note that user input is shown in <mark>**highlighted bold**</mark>):

<pre><code>Starting command loop
1? <mark><b>insert Wombat joice notanint</b></mark>
Command = insert
Parameter 1 = Wombat
Parameter 2 = joice
Parameter 3 = notanint
Expecting int, found "notanint"
2? <mark><b>insert     Wombat    joice 54</b></mark>
Command = insert
Parameter 1 = Wombat
Parameter 2 = joice
Parameter 3 = 54
3? <mark><b>printdb</b></mark>
Command = printdb
Invalid parameter list: printdb
4? <mark><b>insert Wombat 150</b></mark>
Command = insert
Parameter 1 = Wombat
Parameter 2 = 150
Invalid parameter list: insert
5? <mark><b>quit extra junk</b></mark>
Command = quit
Parameter 1 = extra
Parameter 2 = junk
Invalid parameter list: quit
6? <mark><b>printdb Wombat</b></mark>
Command = printdb
Parameter 1 = Wombat
Wombat: name = joice, length = 54cm
7? <mark><b>quit</b></mark>
Command = quit
</code></pre>

### Stage 7

Extend the previous stage so that:

- A table cannot be used (that is, nothing can be inserted into the table) until it has been "added" using the <samp>**
  addtable**</samp> command:<br><br>
  <samp>**addtable table_name**</samp><br><br>

  If an insert is attempted before the table has been added, then the following message should be output. For
  example,<br><br>
  <samp>Invalid table name: Wombat</samp><br><br>

- Include a second table, <samp>*LocationData*</samp>, that accepts insertions of the form:<br><br>
  <samp>**insert LocationData *name x_coordinate y_coordinate***</samp><br><br>

  If either of the coordinate parameters are not integers, or the number of parameters is incorrect (that is, there are
  not 3 parameters), the following message should be output:<br><br>
  <samp>Invalid parameter list: insert</samp><br><br>

- Insertions into tables that are not either <samp>Wombat</samp> or <samp>LocationData</samp> should cause the following
  message to be output:<br><br>
  <samp>Invalid parameter list: insert<samp><br><br>

- Add identification (id) numbers to <samp>Wombats</samp>. It should be generated automatically when a <samp>
  Wombat</samp> is created. The first wombat should have an id number of 1000, the second 1001, and so on.

An example follows (note that user input is shown in <mark>**highlighted bold**</mark>):

<pre><code>Starting command loop
1? <mark><b>insert Wombat fred 50 </b></mark>
Command = insert
Parameter 1 = Wombat
Parameter 2 = fred
Parameter 3 = 50
Invalid table name: Wombat
2? <mark><b>addtable Wombat</b></mark>
Command = addtable
Parameter 1 = Wombat
3? <mark><b>insert Wombat fred 50</b></mark>
Command = insert
Parameter 1 = Wombat
Parameter 2 = fred
Parameter 3 = 50
4? <mark><b>addtable LocationData</b></mark>
Command = addtable
Parameter 1 = LocationData
5? <mark><b>insert LocationData fred 40 50</b></mark>
Command = insert
Parameter 1 = LocationData
Parameter 2 = fred
Parameter 3 = 40
Parameter 4 = 50
6? <mark><b>insert Locationdata julie 30 xxx     </b></mark>
Command = insert
Parameter 1 = Locationdata
Parameter 2 = julie
Parameter 3 = 30
Parameter 4 = xxx
Invalid table name: Locationdata
7? <mark><b>insert LocationData julie 30 xx</b></mark>
Command = insert
Parameter 1 = LocationData
Parameter 2 = julie
Parameter 3 = 30
Parameter 4 = xx
Invalid parameter list: insert
8? <mark><b>printdb Wombat</b></mark>
Command = printdb
Parameter 1 = Wombat
Wombat: id = 1000, name = fred, length = 50cm
9? <mark><b>printdb LocationData</b></mark>
Command = printdb
Parameter 1 = LocationData
fred is at Location java.awt.Point[x=40,y=50]
10? <mark><b>quit</b></mark>
Command = quit
</code></pre>

### <span style="color: red;">This stage is Very Challenging</span>

### Stage 8

Extend stage 7 to include:

- Include a third table, <samp>Frog</samp>, that accepts insertions of the form:<br><br>
  <samp>**insert Frog *name colour weight length***</samp><br><br>
  where *weight* and *length* must be integers.
  <br>(your program should handle the addition of a new table by simply declaring the corresponding
  class and with no modifications to any other code)<br><br>

- A <samp>**select**</samp> command of the form:<br><br>
  <samp>**select *column* from table**</samp><br><br>
  where *column* is an integer and table is the name of a table (<samp>Wombat</samp>, <samp>LocationData</samp> etc.)
  .<br><br>
  The command causes the values in the specified column to be printed out, one per line. For example, if *column* was 1,
  then the names of all wombats would be output. The format of the command should be checked and appropriate error
  messages output (see example below).

An example follows (note that user input is shown in <mark>**highlighted bold**</mark>):

<pre><code>Starting command loop
1? <mark><b>addtable Frog</b></mark>
Command = addtable
Parameter 1 = Frog
2? <mark><b>insert Frog kermit green 50 12</b></mark>
Command = insert
Parameter 1 = Frog
Parameter 2 = kermit
Parameter 3 = green
Parameter 4 = 50
Parameter 5 = 12
3? <mark><b>insert Frog kermit green 50 junk</b></mark>
Command = insert
Parameter 1 = Frog
Parameter 2 = kermit
Parameter 3 = green
Parameter 4 = 50
Parameter 5 = junk
Invalid parameter list: insert
4? <mark><b>insert Frog toad brown 100 20</b></mark>
Command = insert
Parameter 1 = Frog
Parameter 2 = toad
Parameter 3 = brown
Parameter 4 = 100
Parameter 5 = 20
5? <mark><b>select 1 from Frog</b></mark>
Command = select
Parameter 1 = 1
Parameter 2 = from
Parameter 3 = Frog
kermit
toad
6? <mark><b>select 5 from Frog</b></mark>
Command = select
Parameter 1 = 5
Parameter 2 = from
Parameter 3 = Frog
Invalid column index: 5
7? <mark><b>select e from Frog</b></mark>
Command = select
Parameter 1 = e
Parameter 2 = from
Parameter 3 = Frog
Invalid parameter list: select
8? <mark><b>select 2 from Junk</b></mark>
Command = select
Parameter 1 = 2
Parameter 2 = from
Parameter 3 = Junk
Invalid table name: Junk
9? <mark><b>quit</b></mark>
Command = quit
</code></pre>
