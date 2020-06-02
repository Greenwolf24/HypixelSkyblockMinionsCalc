# HypixelSkyblockMinionCalc

Uses Google's Gson 2.8.6.  I'm sure version is fine with anything, it's only needed for parsing json and can be swapped out if needed as it's parse method was depricated.  It's just too simple and useful though so I'm gonna keep using it.

The project right now is only a MinionMaker which also is an item maker.  These makers will save the final minion and items for quick access later.

The end goal of the project is to have an automatic profit calculator without the need of user input on every single run.  This will be done by having the user select the minions they want for their setup, any modifiers on them, and then an automatic API call to Hypixel for the bazaar prices.  For the final calculation there is a little bit of reverse engineering of the hypixel minions.  This reverse engineering would be so near exact results could be calculated instantly.  This could be skipped if each setup was run in a fast-forward run, but would obviously be inefficient and likely slow.
