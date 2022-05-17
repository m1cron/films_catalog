import {$host} from "./index";

export const register = async (username, password) => {
  return await $host.post('/api/v1/auth/register', {username, password}).then(
      response => {
        const data = response.data;
        localStorage.setItem('token', data.data.token)
        return data
      })
}

export const login = async (username, password) => {
  return $host.post('/api/v1/auth/login', {username, password}).then(
      response => {
        const data = response.data;
        localStorage.setItem('token', data.data.token)
        return data
      })
}
