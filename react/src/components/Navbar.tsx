import {Box, Button, Flex, Stack, useColorMode, useColorModeValue, useDisclosure} from '@chakra-ui/react';
import {MoonIcon, SunIcon} from '@chakra-ui/icons';
import {useAppDispatch, useAppSelector} from "../hooks/redux";
import SignUpModal from "./SignUpModal";
import SignInModal from "./SignInModal";
import {useEffect} from "react";
import {useCallback} from "react";
import {saveUser} from "../store/reducers/UserSlice";
import {UserMenu} from "./UserMenu";
import {AuthorizeButtons} from "./AuthorizeButtons";
import {useNavigate} from "react-router-dom";

export default function Navbar() {

  const {colorMode, toggleColorMode} = useColorMode();
  const signInModal = useDisclosure();
  const signUpModal = useDisclosure();

  const navigate = useNavigate();
  const linkTrigger = useCallback(() => navigate('/', {replace: true}), [navigate]);

  const {loggedInUser} = useAppSelector((state) => state.userReducer);
  const dispatch = useAppDispatch();

  useEffect(() => {
    let loggedInUserString = localStorage.getItem("loggedInUser");
    if (loggedInUserString) {
      let loggedInUser = JSON.parse(loggedInUserString);
      dispatch(saveUser(loggedInUser));
    }
  }, []);

  return (
    <>
      <SignInModal isOpen={signInModal.isOpen} onClose={signInModal.onClose}/>
      <SignUpModal isOpen={signUpModal.isOpen} onClose={signUpModal.onClose}/>

      <Box bg={useColorModeValue('gray.100', 'gray.900')} px={4} mb={4}>
        <Flex h={16} alignItems={'center'} justifyContent={'space-between'}>
          <Box onClick={linkTrigger}>
            <Button variant='link'>
              Logo
            </Button>
          </Box>

          <Flex alignItems={'center'}>
            <Stack direction={'row'} spacing={7}>

              <Button onClick={toggleColorMode}>
                {colorMode === 'light' ? <MoonIcon/> : <SunIcon/>}
              </Button>

              {loggedInUser
                ? <UserMenu loggedInUser={loggedInUser}/>
                : <AuthorizeButtons signInOnOpen={signInModal.onOpen} signUpOnOpen={signUpModal.onOpen}/>}
            </Stack>
          </Flex>
        </Flex>
      </Box>
    </>
  );
}