import axios, {AxiosInstance} from "axios";
import JWTUtility from "./JWTUtility";

export default class AxiosUtility {
  private static api: AxiosInstance;

  public static getApi() {
    if (!this.api) {
      this.api = axios.create({
        baseURL: "http://localhost:8080",
        headers: {
          "Content-Type": "application/json",
        }
      });
      this.api.interceptors.request.use(
          (config) => {
            const token = localStorage.getItem('token');
            if (token && !JWTUtility.checkIfIsExpired(token)) {
              config.headers.Authorization = `Bearer ${token}`;
            }
            return config;
          },
          (error) => Promise.reject(error)
      );
    }
    return this.api;
  }
}