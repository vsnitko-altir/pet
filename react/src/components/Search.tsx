import React, {ChangeEvent, useEffect, useState} from "react";
import {Input, useColorMode, useToast} from "@chakra-ui/react";
import {ListGroup} from "react-bootstrap";
import axios from "axios";

type SearchResult = {
  id: string,
  firstName: string,
  lastName: string
}

export default function Search() {

  const [userQuery, setUserQuery] = useState('');
  const onUserQueryChanged = (e: ChangeEvent<HTMLInputElement>) => setUserQuery(e.target.value);
  const [searchResults, setSearchResults] = useState(Array<SearchResult>);

  function findUsers() {
    axios.post("/user/find", {
      query: userQuery
    }).then(response => {
      setSearchResults(response.data);
    }).catch(err => {
      toast({
        title: 'Error',
        status: 'error',
        duration: 5000,
        position: 'bottom-right',
        isClosable: true,
      });
    });
  }

  useEffect(() => {
    findUsers();
  }, [])

  useEffect(() => {
    const delayDebounce = setTimeout(() => {
      findUsers();
    }, 500)

    return () => clearTimeout(delayDebounce)
  }, [userQuery])

  const toast = useToast();
  const {colorMode} = useColorMode();
  const listStyle = {
    color: colorMode === 'light' ? '#1A202C' : 'rgba(255, 255, 255, 0.92)',
    backgroundColor: colorMode === 'light' ? 'white' : '#1A202C',
    borderColor: colorMode === 'light' ? '#E2E8F0' : 'rgba(255, 255, 255, 0.16)',
    zIndex: 0
  }

  return (
    <>
      <Input placeholder='Find user' mb={2} onChange={onUserQueryChanged}/>
      <ListGroup defaultActiveKey="#link1">
        {searchResults.map((searchResult) =>
          <ListGroup.Item key={searchResult.id} action style={listStyle}>
            {searchResult.firstName} {searchResult.lastName}
          </ListGroup.Item>
        )}
      </ListGroup>
    </>
  );
}