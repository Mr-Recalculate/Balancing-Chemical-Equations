# Balancing-Chemical-Equations
Program for Balancing Chemical Equations
Gabriel Young
5/31/2018
The Program takes input of a reactant and a product each consisting of chemical compounds
It then prints the balanced chemical equation consisting of the correct number of reactants and products
This program will probably break if you input a compound with double digits of one element

In my program, the user inputs a full chemical equation and my program prints out the balanced chemical equation which is just the input equation with integer coefficients added that make each side of the equation have the same number of each atom. E.g: (Input equation is first, Output equation is second)

 	K + H2O = KOH + H2
	2K + 2H2O = 2KOH + H2

The above equations represent what happens when pure potassium is dropped in water: Potassium atoms take one oxygen and one hydrogen atom from water molecules to form potassium hydroxide- the remaining hydrogen atoms bond together to form hydrogen gas.
With the guess and check method, you have to plug in random numbers for the coefficients until you stumbled across the correct answer. Using an algebraic method, you assign a variable to each coefficient and create an equation for each element in the equation:

	K + yH2O = zKOH + uH2
	K: x = z
	H: 2y = 2z + u
	O: y =z
	
Then it’s just a matter of solving a system of equations for each variable. I then learned that this is done in code with a bunch Matrix math. Now, I was taking pre-calculus at this point, so Linear Algebra was thoroughly over my head. I worked around this problem by figuring out how to import code libraries to do the Matrix Math for me.
