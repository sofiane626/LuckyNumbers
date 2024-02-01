# Commentaires itération 1

* Les noms de packages doivent être en minuscule. Donc il faut éviter g56411.luckynumbers.LuckyNumbers.
* Attention aux espaces et au lignes vide juste après le nom des méthodes. Par exemple:

```
    public Tile getTile(Position pos) {

        return tiles[pos.getRow()][pos.getColumn()];
    }
```

il faut plutôt faire:

```
    public Tile getTile(Position pos) {
        return tiles[pos.getRow()][pos.getColumn()];
    }
```

* Dans ton controlleur, il ne faut pas faire de System.out.println(), il faut juste appeler des méthodes de ta classe vue.