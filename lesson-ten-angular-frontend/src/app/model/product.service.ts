import {Injectable} from '@angular/core';
import {Product} from "./product";
import {resolve} from "@angular/compiler-cli";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private identity: number = 6;

  private products: { [key: number]: Product } = {
    1: new Product(1, 'Car', 550000),
    2: new Product(2, 'TV', 60000),
    3: new Product(3, 'SSD', 9000),
    4: new Product(4, 'Memory card', 3000),
    5: new Product(5, 'Telephone', 90000),
  };

  constructor(public http: HttpClient) {
  }

  public findAll() {
    return this.http.get<Product[]>('/api/v1/product/all').toPromise();
    // return new Promise<Product[]>((resolve, reject) => {
    //   resolve(
    //     Object.values(this.products)
    //   )
    // })
  }

  public findById(id: number) {
    return new Promise<Product>((resolve, reject) => {
      resolve(
        this.products[id]
      )
    })
  }

  public save(product: Product) {
    return new Promise<void>((resolve, reject) => {
      if (product.id == -1) {
        product.id = this.identity++;
      }
      this.products[product.id] = product;
      resolve();
    })
  }

  public delete(id: number) {
    return new Promise<void>((resolve, reject) => {
      delete this.products[id];
      resolve();
    })
  }
}
