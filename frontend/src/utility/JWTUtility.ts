export const JWT_NO_EXP_CLAIM =
    "No expiration claim found inside the token-payload";

function checkIfIsExpired(jwt: string): boolean {
  const encodedPayload = jwt.split(".")[1];
  const payload = JSON.parse(atob(encodedPayload));
  if (!payload.exp) {
    throw new Error(JWT_NO_EXP_CLAIM);
  }
  return payload.exp * 1000 < Date.now();
}

const JWTUtility = {
  checkIfIsExpired
};

export default JWTUtility;