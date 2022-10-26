import React from 'react';
import ReactDOM from 'react-dom/client';
import {App} from './App';
import {Provider} from "react-redux";
import {setupStore} from "./store/store";
import {BrowserRouter} from "react-router-dom";
import {ChakraProvider} from "@chakra-ui/react";
import axios from "axios";

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);

axios.defaults.baseURL = 'http://localhost:8081';

root.render(
  <Provider store={setupStore()}>
    <BrowserRouter>
      <ChakraProvider>
        <App/>
      </ChakraProvider>
    </BrowserRouter>
  </Provider>
);