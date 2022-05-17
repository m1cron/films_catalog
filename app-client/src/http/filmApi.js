import {$host} from "./index";

export const getById = async (imdbId) => {
  return await $host.get('/api/v1/film/' + imdbId).then(response => {
    return response.data
  })
}

export const getAll = async (pageNum) => {
  return $host.get('/api/v1/film/getAll?pageNum=' + pageNum)
  .then(response => {
    return response.data.data
  })
}
