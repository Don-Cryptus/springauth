export interface LoginRes {
  id: number;
  token: string;
  type: string;
  username: string;
  email: string;
  accessToken: string;
}

export interface LoginReq {
  email: string;
  password: string;
}
