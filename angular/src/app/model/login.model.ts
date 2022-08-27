export interface LoginRes {
  id: number;
  type: string;
  username: string;
  email: string;
  accessToken: string;
  refreshToken: string;
}

export interface LoginReq {
  username: string;
  password: string;
}
