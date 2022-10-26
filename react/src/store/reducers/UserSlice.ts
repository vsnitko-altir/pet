import {User} from "../../model/User";
import {createSlice, PayloadAction} from "@reduxjs/toolkit";

interface UserState {
  loggedInUser: User | null
}

const initialState: UserState = {
  loggedInUser: null
}

export const signUpSlice = createSlice({
  name: 'signIn',
  initialState,
  reducers: {
    saveUser(state, action: PayloadAction<User | null>) {
      state.loggedInUser = action.payload;
    }
  }
})

export const { saveUser } = signUpSlice.actions
export default signUpSlice.reducer;