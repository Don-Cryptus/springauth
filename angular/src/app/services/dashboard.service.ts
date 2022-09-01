import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  constructor(private http: HttpClient) {}

  public all(): Observable<string> {
    return this.http.get<string>(`${environment.url}/api/dashboard/all`, {
      responseType: 'text' as 'json',
    });
  }
}
