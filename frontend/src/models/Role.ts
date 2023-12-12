import Authority from "./Authority";

export default interface Role {
  id: string;
  name: string;
  authorities: Authority[];
}