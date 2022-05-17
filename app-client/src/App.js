import React, {useContext} from "react";
import {BrowserRouter} from "react-router-dom";
import AppRouter from "./components/AppRouter";
import NavBar from "./components/NavBar";
import {observer} from "mobx-react-lite";
import {Context} from "./index";

const App = observer(() => {
  const {user} = useContext(Context)
  return (
      <BrowserRouter>
        <NavBar/>
        <AppRouter/>
      </BrowserRouter>
  );
});

export default App;
