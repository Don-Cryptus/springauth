import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UnsplashResponse } from '../model/unsplash.model';

@Injectable({
  providedIn: 'root',
})
export class UnsplashService {
  constructor(private http: HttpClient) {}

  public searchUnsplash(query: String): Observable<UnsplashResponse> {
    return this.http.get<UnsplashResponse>(
      `${environment.url}/api/dashboard/searchunsplash?query=${query}`
    );
  }
}
