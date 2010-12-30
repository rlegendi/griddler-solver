Readme
======

What is this?
-------------
This program is a demonstration application for evolutionary (actually, genetic)
algorithm how (*not*) to solve nonograms. 

*What are nonograms?* Nonograms are picture logic puzzles in which cells in a
grid have to be colored or left blank according to numbers given at the side of
the grid to reveal a hidden picture. In this puzzle type, the numbers measure
how many unbroken lines of filled-in squares there are in any given row or
column.

For example, a clue of "4 8 3" would mean there are sets of four, eight, and
three filled squares, in that order, with at least one blank square between
successive groups.

*For example*, the following nonogram (it's a chicken, honest) has clues as
shown below:
	
	      1 5
	    1 3 3 1 4 3 2
	  3 . # # # . . .
	2 1 # # . # . . .
	3 2 . # # # . # #
	  5 . . # # # # #
	  4 . . # # # # .
	  1 . . . . # . .
	  2 . . . # # . .

How it works?
-------------

Things to play with
-------------------
* Different fitness function?
* What are the popular file formats for griddlers? Implement support.

Developers
----------
Initial author, target of blame:

* Richard O. Legendi <richard.legendi@gmail.com>

Related reading materials
-------------------------

* [Msc thesis][]
* [NP complete technical paper][]
* [Wiki Nonogram][]
* [NetLogo Nonogram][]

  [Wiki Nonogram]: http://en.wikipedia.org/wiki/Nonogram "Wikipedia: Nonogram"
  [NetLogo Nonogram]: http://ccl.northwestern.edu/netlogo/models/community/Nonogram "NetLogo Nonogram by Robert Holmes with simulated annealing"
