import React, {useContext, useState} from "react";
import {Button, Card, Container, Form, Row} from "react-bootstrap";
import {NavLink, useHistory, useLocation} from "react-router-dom";
import {LOGIN_ROUTE, MAIN_ROUTE, REGISTRATION_ROUTE} from "../utils/consts";
import {login, register} from "../http/authApi";
import {observer} from "mobx-react-lite";
import {Context} from "../index";

const AuthPage = observer(() => {
  const isLogin = useLocation().pathname === LOGIN_ROUTE
  const {user} = useContext(Context)
  const history = useHistory()

  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')

  const click = async () => {
    try {
      let data;
      if (isLogin) {
        data = await login(username, password)
      } else {
        data = await register(username, password)
      }
      user.setUser(data.data)
      user.setIsAuth(true)
      history.push(MAIN_ROUTE)
    } catch (e) {
      alert(e.response.data.error.message)
    }
  }

  return (
      <Container
          className="d-flex justify-content-center align-items-center"
          style={{height: window.innerHeight - 54}}
      >
        <Card style={{width: 600}} className="p-5">
          <h2 className="m-auto">{isLogin ? 'Log in' : 'Sign up'}</h2>
          <Form className="d-flex flex-column">
            <Form.Control
                className="mt-3"
                placeholder="Username"
                value={username}
                onChange={e => setUsername(e.target.value)}
            />
            <Form.Control
                className="mt-3"
                placeholder="Password"
                value={password}
                onChange={e => setPassword(e.target.value)}
                type="password"
            />
            <Row className="d-flex justify-content-between mt-3 pl-3 pl-3">
              {isLogin ?
                  <div>
                    Not registered? <NavLink to={REGISTRATION_ROUTE}>Create an
                    account.</NavLink>
                  </div>
                  :
                  <div>
                    Registered? <NavLink to={LOGIN_ROUTE}>Log in</NavLink>
                  </div>}
              <Button variant={"outline-success"} onClick={click}>
                {isLogin ? 'Log in' : 'Sign up'}
              </Button>
            </Row>
          </Form>
        </Card>
      </Container>
  )
});

export default AuthPage;
