import React from "react";
import {observer} from "mobx-react-lite";
import {useContext, useEffect, useState} from "react";
import {Context} from "../index";
import {Container} from "react-bootstrap";
import FilmList from "../components/FilmList";
import {getFavourites} from "../http/favouriteApi";

const FavouriteFilmsPage = observer(() => {
  const {user} = useContext(Context)
  const [films, setFilms] = useState([])
  const [currentPage, setCurrentPage] = useState(1)
  const [fetching, setFetching] = useState(true)
  const [totalCount, setTotalCount] = useState(0)

  useEffect(() => {
    if (fetching) {
      getFavourites(user.user.uuid, currentPage)
      .then(data => {
        setFilms([...films, ...data.data])
        setCurrentPage(prevState => prevState + 1)
        setTotalCount(data.pageable.totalCount)
      })
      .finally(() => setFetching(false))
    }
  }, [fetching])

  useEffect(() => {
    document.addEventListener('scroll', scrollHandler)
    return function () {
      document.removeEventListener('scroll', scrollHandler)
    }
  })

  const scrollHandler = (e) => {
    if (e.target.documentElement.scrollHeight
        - e.target.documentElement.scrollTop - window.innerHeight < 100
        && films.length < totalCount) {
      setFetching(true)
    }
  }

  return (
      <Container>
        <FilmList films={films}/>
      </Container>
  );
});

export default FavouriteFilmsPage;