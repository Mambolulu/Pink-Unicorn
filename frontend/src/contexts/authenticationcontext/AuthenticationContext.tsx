import React, { createContext, ReactNode, useContext, useEffect, useReducer, useState } from "react";
import User from "../../models/User";
import Authority from "../../models/Authority";
import { AxiosInstance } from "axios";
import AxiosUtility from "../../utility/AxiosUtility";

// Authentication Context Props
interface AuthenticationContextProps {
  principal: User | undefined;
  setPrincipal: (user: User | undefined) => void;
  hasAnyAuthority: (authorities: Authority["name"][]) => boolean;
  logout: () => Promise<void>;
}

// Action Types
enum ActionTypes {
  LOADING,
  FAILED,
  AUTHENTICATED
}

// Authentication Context
export const AuthenticationContext = createContext<AuthenticationContextProps>({} as AuthenticationContextProps);

// useAuth Hook
export const useAuth = () => {
  return useContext(AuthenticationContext);
};

// AuthenticationContextProvider Props
interface AuthenticationContextProviderProps {
  children: ReactNode;
}

function Navigate(props: { to: string }) {
  return null;
}

// AuthenticationContextProvider Component
const AuthenticationContextProvider = ({ children }: AuthenticationContextProviderProps) => {
  const api: AxiosInstance = AxiosUtility.getApi();
  const [principal, setPrincipal] = useState<User | undefined>();
  const [state, dispatch] = useReducer((state: ActionTypes, action: ActionTypes) => action, ActionTypes.LOADING);

  // Authenticate Function
  useEffect(() => {
    const authenticate = async () => {
      try {
        const token = localStorage.getItem('token');
        if (token) {
          const response = await api.get('/users/profile');
          setPrincipal(response.data);
          dispatch(ActionTypes.AUTHENTICATED);
        } else {
          dispatch(ActionTypes.FAILED);
        }
      } catch {
        dispatch(ActionTypes.FAILED);
      }
    };

    authenticate();
  }, [api]);

  // hasAnyAuthority Function
  const hasAnyAuthority = (authorities: Authority["name"][]): boolean => {
    return principal?.roles.some(role => role.authorities.some(auth => authorities.includes(auth.name))) ?? false;
  };

  // Logout Function
  const logout = async () => {
    localStorage.removeItem('token');
    setPrincipal(undefined);
    dispatch(ActionTypes.FAILED);
  };

  // Render Function
  const renderContent = () => {
    switch (state) {
      case ActionTypes.LOADING:
        return <p>Loading...</p>;
      case ActionTypes.FAILED:
        return <Navigate to="/login" />;
      case ActionTypes.AUTHENTICATED:
        return children;
    }
  };

  return (
      <AuthenticationContext.Provider value={{ principal, setPrincipal, hasAnyAuthority, logout }}>
        {renderContent()}
      </AuthenticationContext.Provider>
  );
};

export default AuthenticationContextProvider;
