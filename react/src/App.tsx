import React from 'react';
import {Routes} from "react-router-dom";
import {Route} from "react-router-dom";
import {Link} from "react-router-dom";
import {NotFound} from "./components/NotFound";
import Navbar from "./components/Navbar";
import Homepage from "./components/Homepage";
import EditPage from "./components/EditPage";

const App = () => {

  return (
    <div>
      <Navbar/>
      <Routes>
        <Route path="/" element={<Homepage/>}/>
        <Route path="*" element={<NotFound/>}/>
        <Route path="/edit" element={<EditPage/>}/>
      </Routes>
    </div>
  );
};

export {App};