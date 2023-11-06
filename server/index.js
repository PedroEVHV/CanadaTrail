import { ApolloServer } from '@apollo/server';
import { startStandaloneServer } from '@apollo/server/standalone';
import * as fs from 'node:fs';


import {characterInit} from "./data_init/characters.js";
import {locationsInit} from "./data_init/locations.js";
import {itemsInit} from "./data_init/items.js"
import { eventsInit } from './data_init/events.js';
import { gameSetup } from './game_setup/default_setup.js';

// A schema is a collection of type definitions (hence "typeDefs")
// that together define the "shape" of queries that are executed against
// your data.

const characters = characterInit();
const items = itemsInit();
const locations = locationsInit();
const events = eventsInit();
const game_setup = gameSetup();
const typeDefs = fs.readFileSync('./types.graphql', { encoding: 'utf8' });
const resolvers = {
  Query : {
    characters : () => characters,
    items : () => items,
    locations : () => locations,
    events : () => events,
    setup : () => game_setup,
  }
}

const server = new ApolloServer({
  typeDefs,
  resolvers,
});

// Passing an ApolloServer instance to the `startStandaloneServer` function:
//  1. creates an Express app
//  2. installs your ApolloServer instance as middleware
//  3. prepares your app to handle incoming requests
const { url } = await startStandaloneServer(server, {
  listen: { port: 4000 },
  cors: {
      origin: true,
      credentials: true, // true if you need cookies/authentication
      methods: ['GET', 'POST', 'OPTIONS'],
    },
});

console.log(`🚀  Server ready at: ${url}`);
