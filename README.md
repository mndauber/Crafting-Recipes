# Crafting-Recipes
Problem #19 from HP CodeWars 2016 - Crafting Recipes
My first thought/attempt at this problem was to store the data using an ArrayList of ArrayLists of String Arrays. 
This obviously doesnt work. Trying to set up the object was a nightmare, not even talking about getting the data out of that mess.
I then decided to use an Array of Arraylists of Strings. This worked well in terms of having each line stored in a convinient manner.
After getting the data stored I tried to come up with a way to use the contents of the recipe to call the recipe of the items in the recipe. This was a flawed viewpoint, because the only way to use the name as a parameter to get a number(that I knew of at the time), was arraylists, but I already knew that that wouldn't work. I asked my teacher for ideas, and she suggested a map. I found some information on maps online, and decided to use a treemap, because 
