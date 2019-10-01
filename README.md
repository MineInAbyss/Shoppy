# Shoppy

Shoppy is a Shopkeeper plugin originally intended for the MineInAbyss server.

It is currently in development and won't have any proper builds for a while.

It is designed to hold many types of shops, which work with currency, items, and much more.

# Outline

The following section is meant to serve as a guide in the design of the plugin itself.

### Core idea

A shop simply stores a list of trades. It is up to the shop's implementation to display these.

A trade holds a list of `Want`s and `Reward`s.

The shop displays wants and rewards and gives a way to attempt a transaction.

If the wants' conditions are met, it will play out a list of rewards.

### Serialization and storing of data

Trades are serializable using Bukkit's config methods, but it is up to shops to choose how to save these. The most basic right-click-to-interact shopkeeper could store this under its UUID, white a shop that's usable anywhere could just store trades under a specific file name. 

Shops can implement trades however they want. A shop can make a gui or work through interactions with the physical world, it just needs to store a trade/trades and have a way of identifying which trade is associated with it.

### Registering additional wants and rewards

The plugin will in the future allow other plugins, such as MineInAbyss to register their own wants (e.x. player whistle level), and rewards (e.x. levelling up)