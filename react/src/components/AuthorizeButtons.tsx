import {Button} from "@chakra-ui/react";


export function AuthorizeButtons({signInOnOpen, signUpOnOpen}: { signInOnOpen: () => void, signUpOnOpen: () => void }) {
  return <>
    <Button
      fontSize={'sm'}
      fontWeight={600}
      variant='outline'
      onClick={signInOnOpen}
    >
      Sign In
    </Button>
    <Button
      onClick={signUpOnOpen}
      fontSize={'sm'}
      fontWeight={600}
      color={'white'}
      bg={'pink.400'}
      _hover={{
        bg: 'pink.300',
      }}
    >
      Sign Up
    </Button>
  </>;
}