import Role from "./Role";

export default interface User {
  id: string;
  firstName: string;
  lastName: string;
  email: string;
  roles: Role[];
}