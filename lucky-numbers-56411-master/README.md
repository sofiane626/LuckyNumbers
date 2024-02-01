for the beginning of the game i choose to take four random tile for each player
sort them and put them in the board of all the player one at a time
(4 at a time for each player)
example
we have two player
i choose 4 tiles i put them for the player 0
and then same thing for player 1
and then the game can start

i choose that to avoid exception or just to avoid the fact that the current
player put the tiles at the wrong place because we'll have to replace all
the other tile

for the model i had to change the method putTile
i just added something to check if there is already a tile in the position given
to drop the previous tile at this position
or juste put the new one (if we can put the picked tile)

then i added some modification to certain exception because we added the state
PLACE_OR_DROP_TILE

i also changed the winner condition to return the right winner because now we
can have one or as many winners as possible
