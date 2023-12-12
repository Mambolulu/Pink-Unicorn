import {Navigate, Outlet} from 'react-router-dom';
import {useAuth} from "../contexts/authenticationcontext/AuthenticationContext";
import Authority from "../models/Authority";

export interface ProtectedProps {
  authoritiesToGrantAccess: Authority["name"][];
};

export const Protected = ({authoritiesToGrantAccess}: ProtectedProps) => {
  const {principal, hasAnyAuthority} = useAuth();
  return principal && hasAnyAuthority(authoritiesToGrantAccess) ? <Outlet/> : <Navigate to={"/login"}/>
};