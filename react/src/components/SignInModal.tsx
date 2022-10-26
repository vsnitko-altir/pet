import React from 'react';
import {useState} from "react";
import {ChangeEvent} from "react";
import {useRef} from "react";
import {ModalOverlay} from "@chakra-ui/react";
import {ModalContent} from "@chakra-ui/react";
import {ModalHeader} from "@chakra-ui/react";
import {ModalCloseButton} from "@chakra-ui/react";
import {ModalBody} from "@chakra-ui/react";
import {FormControl} from "@chakra-ui/react";
import {FormLabel} from "@chakra-ui/react";
import {Input} from "@chakra-ui/react";
import {ModalFooter} from "@chakra-ui/react";
import {Button} from "@chakra-ui/react";
import {Modal} from "@chakra-ui/react";
import axios from "axios";
import {useToast} from "@chakra-ui/react";
import {useAppDispatch} from "../hooks/redux";
import { saveUser } from '../store/reducers/UserSlice';
import {InputRightElement} from "@chakra-ui/react";
import {InputGroup} from "@chakra-ui/react";

export default function SignInModal({isOpen, onClose}: { isOpen: boolean, onClose: () => void }) {

  const [email, setEmail] = useState('');
  const onEmailChanged = (e: ChangeEvent<HTMLInputElement>) => setEmail(e.target.value);
  const [password, setPassword] = useState('');
  const onPasswordChanged = (e: ChangeEvent<HTMLInputElement>) => setPassword(e.target.value);
  const [showPassword, setShowPassword] = useState(false);
  const onShowPasswordChanged = () => setShowPassword(!showPassword);

  const initialRef = useRef(null);
  const finalRef = useRef(null);
  const toast = useToast();

  const dispatch = useAppDispatch();

  const signIn = () => {
    axios.post("/sign-in", {
      email: email,
      password: password
    }, {withCredentials: true}).then(response => {
      dispatch(saveUser(response.data));
      localStorage.setItem("loggedInUser", JSON.stringify(response.data));
      onClose();
    }).catch(err => {
      toast({
        title: 'Password is not correct',
        status: 'error',
        duration: 5000,
        position: 'bottom-right',
        isClosable: true,
      });
    });
  }

  return (
    <div>
      <Modal
        initialFocusRef={initialRef}
        finalFocusRef={finalRef}
        isOpen={isOpen}
        onClose={onClose}
      >
        <ModalOverlay/>
        <ModalContent>
          <ModalHeader>Sign In</ModalHeader>
          <ModalCloseButton/>
          <ModalBody pb={6}>
            <FormControl>
              <FormLabel>Email</FormLabel>
              <Input
                ref={initialRef}
                value={email}
                onChange={onEmailChanged}
                placeholder='Email'/>
            </FormControl>
            <FormControl mt={4}>
              <FormLabel>Password</FormLabel>
              <InputGroup size='md'>
                <Input
                  type={showPassword ? 'text' : 'password'}
                  value={password}
                  onChange={onPasswordChanged}
                  placeholder='Password'
                />
                <InputRightElement width='4.5rem'>
                  <Button h='1.75rem' size='sm' onClick={onShowPasswordChanged}>
                    {showPassword ? 'Hide' : 'Show'}
                  </Button>
                </InputRightElement>
              </InputGroup>
            </FormControl>
          </ModalBody>

          <ModalFooter>
            <Button onClick={signIn} colorScheme='blue' mr={3}>
              Sign In
            </Button>
            <Button onClick={onClose}>Cancel</Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </div>
  );
};