import {User} from "../model/User";
import {Menu} from "@chakra-ui/react";
import {MenuButton} from "@chakra-ui/react";
import {Button} from "@chakra-ui/react";
import {Avatar} from "@chakra-ui/react";
import {MenuList} from "@chakra-ui/react";
import {Center} from "@chakra-ui/react";
import {MenuDivider} from "@chakra-ui/react";
import {MenuItem} from "@chakra-ui/react";
import {useAppDispatch} from "../hooks/redux";
import {saveUser} from "../store/reducers/UserSlice";
import {Link} from "react-router-dom";
import {useCallback} from "react";
import {useNavigate} from "react-router-dom";

export function UserMenu({loggedInUser}: { loggedInUser: User }) {

  const navigate = useNavigate();
  const linkTrigger = useCallback(() => navigate('/edit', {replace: true}), [navigate]);

  const dispatch = useAppDispatch();

  const logout = () => {
    localStorage.removeItem("loggedInUser");
    dispatch(saveUser(null));
  }

  return <Menu>
    <MenuButton
      as={Button}
      rounded={'full'}
      variant={'link'}
      cursor={'pointer'}
      minW={0}>
      <Avatar
        size={'sm'}
        src={'https://avatars.dicebear.com/api/male/username.svg'}
      />
    </MenuButton>
    <MenuList alignItems={'center'}>
      <br/>
      <Center>
        <Avatar
          size={'2xl'}
          src={'https://avatars.dicebear.com/api/male/username.svg'}
        />
      </Center>
      <br/>
      <Center>
        <p>{loggedInUser.firstName} {loggedInUser.lastName}</p>
      </Center>
      <br/>
      <MenuDivider/>
        <MenuItem onClick={linkTrigger}>
          Account Settings
        </MenuItem>
      <MenuItem onClick={logout}>Logout</MenuItem>
    </MenuList>
  </Menu>;
}