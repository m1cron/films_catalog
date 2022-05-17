import React, {useContext} from 'react';
import {Context} from "../index";
import {
  ADMIN_ROUTE,
  FAVORITE_FILMS,
  LOGIN_ROUTE,
  MAIN_ROUTE
} from "../utils/consts";
import {Button, Container, Nav, Navbar, NavLink} from "react-bootstrap";
import {observer} from "mobx-react-lite";
import {useHistory} from "react-router-dom";

const NavBar = observer(() => {
  const {user} = useContext(Context)
  const history = useHistory()

  const logOut = () => {
    user.setUser({})
    user.setIsAuth(false)
  }

  return (
      <Navbar bg="dark" variant="dark">
        <Container>
          <NavLink style={{color: 'white'}}
                   to={MAIN_ROUTE}
                   onClick={() => history.push(MAIN_ROUTE)}
          >
            Simple Film Catalog
          </NavLink>
          {user.isAuth ?
              <Nav className="float-right" style={{color: 'white'}}>
                {user.user.roles.includes('ROLE_ADMIN') ?
                    <Button
                        variant={"outline-light"}
                        onClick={() => history.push(ADMIN_ROUTE)}
                    >
                      Admin Panel
                    </Button> : null}
                <Button
                    className="ml-1"
                    variant={"outline-light"}
                    onClick={() => history.push(FAVORITE_FILMS)}
                >
                  Favourites
                </Button>
                <Button
                    className="ml-1"
                    variant={"outline-light"}
                    onClick={() => logOut()}
                >
                  Log Out
                </Button>
              </Nav>
              :
              <Nav className="float-right" style={{color: 'white'}}>
                <Button
                    variant={"outline-light"}
                    onClick={() => history.push(LOGIN_ROUTE)}
                >
                  Log In
                </Button>
              </Nav>
          }
        </Container>
      </Navbar>
  );
});

export default NavBar;
