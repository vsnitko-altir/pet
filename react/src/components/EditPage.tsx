import React, {ChangeEvent, useEffect, useState} from 'react';
import {Box, Button, Flex, FormControl, FormLabel, Input, useToast} from "@chakra-ui/react";
import axios from "axios";
import {User} from "../model/User";
import {useAppDispatch} from "../hooks/redux";
import {saveUser} from "../store/reducers/UserSlice";

export default function EditPage() {

  const [firstName, setFirstName] = useState('');
  const onFirstNameChanged = (e: ChangeEvent<HTMLInputElement>) => setFirstName(e.target.value);
  const [lastName, setLastName] = useState('');
  const onLastNameChanged = (e: ChangeEvent<HTMLInputElement>) => setLastName(e.target.value);

  const dispatch = useAppDispatch();
  const toast = useToast();

  function getCurrentUserId() {
    const item: string | null = localStorage.getItem("loggedInUser");
    if (item == null) {
      return 0;
    }
    const user: User = JSON.parse(item);
    return user.id;
  }

  const edit = () => {
    axios.post("/user/edit", {
      id: getCurrentUserId(),
      firstName: firstName,
      lastName: lastName
    }, {withCredentials: true}).then(response => {
      dispatch(saveUser(response.data));
      toast({
        title: 'User was updated',
        status: 'success',
        duration: 5000,
        position: 'bottom-right',
        isClosable: true,
      });
    }).catch(err => {
      toast({
        title: err.message,
        status: 'error',
        duration: 5000,
        position: 'bottom-right',
        isClosable: true,
      });
    });
  }

  useEffect(() => {
    axios.get("/user/" + getCurrentUserId())
      .then(response => {
        setFirstName(response.data.firstName);
        setLastName(response.data.lastName);
      });
  }, [])

  return (
    <div>
      <Flex justifyContent="center">
        <Box w="40%">
          <FormControl mt={4}>
            <FormLabel>First name</FormLabel>
            <Input
              value={firstName}
              onChange={onFirstNameChanged}/>
          </FormControl>
          <FormControl mt={4}>
            <FormLabel>Last name</FormLabel>
            <Input
              value={lastName}
              onChange={onLastNameChanged}/>
          </FormControl>
          <Button
            onClick={edit}
            mt={4}
            fontSize={'sm'}
            fontWeight={600}
            color={'white'}
            bg={'pink.400'}
            _hover={{
              bg: 'pink.300',
            }}>
            Save changes
          </Button>
        </Box>
      </Flex>
    </div>
  );
};