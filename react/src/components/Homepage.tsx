import React from 'react';
import Search from "./Search";
import {Flex} from "@chakra-ui/react";
import {Box} from "@chakra-ui/react";

export default function Homepage() {

  return (
    <div>
      <Flex justifyContent="center">
        <Box w="80%">
          <Search/>
        </Box>
      </Flex>
    </div>
  );
};