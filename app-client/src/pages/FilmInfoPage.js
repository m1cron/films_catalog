import React, {useContext, useEffect, useState} from "react";
import {Col, Container, Image, Row} from "react-bootstrap";
import {useParams} from "react-router-dom";
import {getById} from "../http/filmApi";
import emptyStar from "../assets/star.png";
import yellowStar from "../assets/yellowStar.png";
import {Context} from "../index";
import {
  addFavourite,
  checkIsFavourite,
  removeFavourite
} from "../http/favouriteApi";

const FilmInfoPage = () => {
  const {id} = useParams()
  const [film, setFilm] = useState({})
  const [check, setCheck] = useState()
  const {user} = useContext(Context)

  useEffect(() => {
    getById(id).then(data => setFilm(data.data))

    if (user.isAuth) {
      checkIsFavourite(user.user.uuid, id).then(data => setCheck(data.data))
    }
  }, [])

  return (
      <Container className="mt-4">
        <Row>
          <Col md={3}>
            <Image width={300} height={500} src={film.Poster}/>
          </Col>
          <Col className="ml-5" md={8}>
            <Row className="d-flex flex-column align-content-center">
              <div className="d-flex align-content-between">
                <Image width={25} height={25} src={
                  user.isAuth && check
                      ? yellowStar
                      : emptyStar
                }
                       className="mt-2 mr-1"
                       onClick={() => {
                         if (user.isAuth) {
                           if (check) {
                             removeFavourite(user.user.uuid, id)
                             setCheck(false)
                           } else {
                             addFavourite(user.user.uuid, id)
                             setCheck(true)
                           }
                         }
                       }}/>
                <h2>{film.Title}</h2>
              </div>
              <Row>
                <h6 className="mt-1 ml-5 d-flex align-items-center">
                  {film.Year} | {film.Genre} | {film.Country} | {film.Runtime} | {film.imdbRating}
                  <Image width={18} height={18} src={emptyStar}/>
                </h6>
              </Row>
              <Row className="d-flex flex-column m-3">
                <Row style={{
                  background: 'transparent',
                  padding: 'auto',
                  textIndent: 32
                }}>
                  <p className="text-left">{film.Plot}</p>
                </Row>
                <Row style={{background: 'lightgray', padding: 10}}>
                  Director: {film.Director}
                </Row>
                <Row style={{background: 'transparent', padding: 10}}>
                  Actors: {film.Actors}
                </Row>
                <Row style={{background: 'lightgray', padding: 10}}>
                  Writers: {film.Writer}
                </Row>
                <Row style={{background: 'transparent', padding: 10}}>
                  Awards: {film.Awards}
                </Row>
                <Row style={{background: 'lightgray', padding: 10}}>
                  Box Office: {film.BoxOffice}
                </Row>
                <Row style={{background: 'transparent', padding: 10}}>
                  Released: {film.Released}
                </Row>
                <Row style={{background: 'lightgray', padding: 10}}>
                  Language: {film.Language}
                </Row>
              </Row>
            </Row>
          </Col>
        </Row>
      </Container>
  );
}

export default FilmInfoPage;