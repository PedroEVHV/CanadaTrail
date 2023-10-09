import { ApolloServer } from '@apollo/server';
import { startStandaloneServer } from '@apollo/server/standalone';
import * as fs from 'node:fs';

// A schema is a collection of type definitions (hence "typeDefs")
// that together define the "shape" of queries that are executed against
// your data.
const typeDefs = fs.readFileSync('./types.graphql', { encoding: 'utf8' });
const resolvers = {
  Query : {
    characters : () => characters,
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
