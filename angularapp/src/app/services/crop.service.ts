import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Crop } from '../models/crop.model';
import { Observable } from 'rxjs';
import { apiUrl } from '../app.constants';
@Injectable({
  providedIn: 'root'
})
export class CropService {

  constructor(private readonly http: HttpClient) { }

  addCrop(newCrop: Crop): Observable<any> {
    return this.http.post<any>(`${apiUrl}/api/crop`, newCrop);
  }

  getCropByUserId(id: number): Observable<Crop[]> {
    return this.http.get<Crop[]>(`${apiUrl}/api/crop/user/${id}`);
  }

  getCropById(id: number): Observable<any> {
    return this.http.get<any>(`${apiUrl}/api/crop/${id}`);
  }

  getAllCrops(id: number): Observable<any> {
    return this.http.get<any>(`${apiUrl}/api/crop/user/${id}`);
  }

  deleteCropById(cropId: number): Observable<any> {
    return this.http.delete<any>(`${apiUrl}/api/crop/${cropId}`);
  }

  updateCrop(id: number, newCrop: Crop): Observable<any> {
    return this.http.put<any>(`${apiUrl}/api/crop/${id}`, newCrop);
  }
}
