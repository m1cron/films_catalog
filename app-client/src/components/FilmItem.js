import React from "react";
import {Card, Col, Image} from "react-bootstrap";
import star from '../assets/star.png'
import {useHistory} from "react-router-dom";
import {FILM_INFO} from "../utils/consts";

const FilmItem = ({film}) => {
  const history = useHistory()

  return (
      <Col md={3} className={"mt-4"} onClick={() =>history.push(FILM_INFO + '/' + film.imdbID)}>
        <Card style={{width: 220, cursor: 'pointer'}} border={"light"}>
          <Image width={220} height={350} src={film.Poster}/>
          <div
              className="mt-1 d-flex justify-content-between align-items-baseline">
            <div>{film.Title}</div>
            <div className="d-flex align-items-center">
              <div>{film.imdbRating}</div>
              <Image width={18} height={18} src={star}/>
            </div>
          </div>
        </Card>
      </Col>
  );
}

export default FilmItem;
