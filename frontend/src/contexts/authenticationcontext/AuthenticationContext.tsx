import React, {createContext, ReactNode, useContext, useEffect, useReducer, useState} from "react";
import User from "../../models/User";
import {Navigate, redirect} from "react-router-dom";
import {AxiosInstance} from "axios";
import AxiosUtility from "../../utility/AxiosUtility";
import Authority from "../../models/Authority";

interface AuthenticationContextProps {
  principal: User | undefined;
  hasAnyAuthority: (authorities: Authority["name"][]) => boolean;
  logout: () => Promise<void>;
}

enum ActionTypes {
  LOADING,
  FAILED,
  AUTHENTICATED
}

export const AuthenticationContext = createContext<AuthenticationContextProps>(
    {} as AuthenticationContextProps
);

export const useAuth = () => {
  return useContext(AuthenticationContext);
};

export interface AuthenticationContextProviderProps {
  children: ReactNode;
}

const AuthenticationContextProvider = ({children}: AuthenticationContextProviderProps) => {
  const api: AxiosInstance = AxiosUtility.getApi()
  const [principal, setPrincipal] = useState<User | undefined>();
  const reducer = (state: ActionTypes, action: ActionTypes) => {
    switch (action) {
      case ActionTypes.LOADING:
        return ActionTypes.LOADING;
      case ActionTypes.FAILED:
        return ActionTypes.FAILED;
      case ActionTypes.AUTHENTICATED:
        return ActionTypes.AUTHENTICATED;
    }
  }
  const [state, dispatch] = useReducer(reducer, ActionTypes.LOADING)

  const authenticate = async () => {
    try {
      const response = await api.post('/users/login', {"email": "max@mustermann","password": "TO_BE_REPLACED"});
      if (response.headers.hasAuthorization) {
        //@ts-ignore
        localStorage.setItem('token', response.headers.getAuthorization());
        //TODO: call backend to get principal (e.g through endpoint /users/profile) and pass it to setPrincipal(). The current Max Mustermann is just a mock!
        setPrincipal({
          id: "37bbc595-71cf-4080-b15c-6848d2d8d05c",
          firstName: "Max",
          lastName: "Mustermann",
          email: "max@mustermann",
          roles: [{
            id: "38bbc595-71cf-4080-b15c-6848d2d8d05c",
            name: "CLIENT",
            authorities: [{
              id: "39bbc595-71cf-4080-b15c-6848d2d8d05c",
              name: "CAN_PLACE_ORDER"
            }, {
              id: "40bbc595-71cf-4080-b15c-6848d2d8d05c",
              name: "'CAN_RETRIEVE_PURCHASE_HISTORY"
            }, {
              id: "41bbc595-71cf-4080-b15c-6848d2d8d05c",
              name: "CAN_RETRIEVE_PRODUCTS"
            }]
          }]
        })
        dispatch(ActionTypes.AUTHENTICATED)
      } else {
        dispatch(ActionTypes.FAILED)
      }
    } catch {
      dispatch(ActionTypes.FAILED)
    }
  }

  useEffect(() => {
    authenticate()
  }, [])

  //TODO: implement hasAnyAuthority() method. Check if principal has any of the authorities passed as parameter
  const hasAnyAuthority = (authorities: Authority["name"][]): boolean => {
    return false;
  }

  const logout = async () => {
    setPrincipal(undefined);
    redirect("/login")
  }

  const renderContent = () => {
    switch (state) {
      case ActionTypes.LOADING:
        return <p>LOADING...</p>;
      case ActionTypes.FAILED:
        return <Navigate to={"/login"}/>
      case ActionTypes.AUTHENTICATED:
        return children;
    }
  }

  return (
      <AuthenticationContext.Provider
          value={{
            principal,
            hasAnyAuthority,
            logout
          }}
      >
        {renderContent()}
      </AuthenticationContext.Provider>
  );
};

export default AuthenticationContextProvider;