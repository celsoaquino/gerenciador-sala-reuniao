import {Room} from './room';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private REST_API_SERVER = 'http://localhost:8080/api/v1/rooms/';

  constructor(private http: HttpClient) {
  }

  public getRoom(id: number): Observable<any> {
    return this.http.get(this.REST_API_SERVER + id);
  }

  createRoom(room: Object): Observable<Object> {
    return this.http.post(this.REST_API_SERVER, room);
  }

  updateRoom(id: number, value: any): Observable<Object> {
    return this.http.put(this.REST_API_SERVER + id, value);
  }

  public getRoomList(): Observable<any> {
    return this.http.get(this.REST_API_SERVER);
  }


  public deleteRoom(id: number): Observable<any> {
    return this.http.delete(this.REST_API_SERVER + `${id}`, {responseType: 'text'});
  }
}
