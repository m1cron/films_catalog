import React from 'react';
import {Row} from "react-bootstrap";
import FilmItem from "./FilmItem";
import {observer} from "mobx-react-lite";

const FilmList = observer(({films}) => {
  return (
      <Row className="d-flex">
        {films.map(film =>
            <FilmItem key={film.imdbID} film={film}/>
        )}
      </Row>
  )
});

export default FilmList;