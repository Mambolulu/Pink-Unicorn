import React, {createContext, ReactNode, useContext, useEffect, useReducer, useState} from "react";
import User from "../../models/User";
import {Navigate, redirect} from "react-router-dom";
import {AxiosInstance} from "axios";
import AxiosUtility from "../../utility/AxiosUtility";
import Authority from "../../models/Authority";

interface AuthenticationContextProps {
  principal: User | undefined;
  isAuthenticated: boolean;
  hasAnyAuthority: (authorities: Authority["name"][]) => boolean;
  logout: () => Promise<void>;
  authenticate: () => Promise<void>;
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

const AuthenticationContextProvider = ({ children }: AuthenticationContextProviderProps) => {
  const api: AxiosInstance = AxiosUtility.getApi();
  const [principal, setPrincipal] = useState<User | undefined>();
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);

  const reducer = (state: ActionTypes, action: ActionTypes) => {
    switch (action) {
      case ActionTypes.LOADING:
        return ActionTypes.LOADING;
      case ActionTypes.FAILED:
        return ActionTypes.FAILED;
      case ActionTypes.AUTHENTICATED:
        return ActionTypes.AUTHENTICATED;
    }
  };

  const [state, dispatch] = useReducer(reducer, ActionTypes.LOADING);

  const authenticate = async () => {
    try {
      const token = localStorage.getItem('token');
      if (token) {

        const profileResponse = await api.get('/users/profile');

        setPrincipal(profileResponse.data);

        dispatch(ActionTypes.AUTHENTICATED);
      } else {
        dispatch(ActionTypes.FAILED);
      }
    } catch {
      dispatch(ActionTypes.FAILED);
    }
  };

  useEffect(() => {
    authenticate();
  }, []);

  const hasAnyAuthority = (authorities: Authority["name"][]): boolean => {
    if (!principal) {
      return false;
    }

    for (const authority of authorities) {
      for (const role of principal.roles) {
        if (role.authorities.find(auth => auth.name === authority)) {
          return true;
        }
      }
    }

    return false;
  };


  const logout = async () => {
    localStorage.removeItem('token'); // Remove the token from local storage
    redirect('/login'); // Redirect to the login page
  };

  const renderContent = () => {
    switch (state) {
      case ActionTypes.LOADING:
        return <p>LOADING...</p>;
      case ActionTypes.FAILED:
        //return <Navigate to={"/login"}/>
      case ActionTypes.AUTHENTICATED:
        return children;
    }
  }

  return (
      <AuthenticationContext.Provider
          value={{
            principal,
            isAuthenticated,
            hasAnyAuthority,
            logout,
            authenticate
          }}
      >
        {renderContent()}
      </AuthenticationContext.Provider>
  );
};

export default AuthenticationContextProvider;