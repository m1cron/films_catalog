import {$authHost} from "./index";

export const getFavourites = async (userId, pageNum) => {
  return $authHost.get(
      '/api/v1/favourite?uuid=' + userId + '&pageNum=' + pageNum)
  .then(
      response => {
        return response.data.data
      })
}

export const checkIsFavourite = async (userId, imdbId) => {
  return $authHost.post('/api/v1/favourite/checkIsFavourite',
      {userId, imdbId})
  .then(
      response => {
        return response.data
      })
}

export const addFavourite = async (userId, imdbId) => {
  return await $authHost.post('/api/v1/favourite/add', {userId, imdbId}).then(
      response => {
        return response.data
      })
}

export const removeFavourite = async (userId, imdbId) => {
  return $authHost.post('/api/v1/favourite/remove', {userId, imdbId}).then(
      response => {
        return response.data
      })
}
