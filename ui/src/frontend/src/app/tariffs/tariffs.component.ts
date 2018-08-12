import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import 'rxjs/add/operator/finally';
import {TariffsService} from "../shared/tariffs.service";

@Component({
  templateUrl: 'tariffs.component.html',
  styleUrls: ['tariffs.component.css']
})
export class TariffsComponent implements OnInit {
  @ViewChild('fileInput') fileInput: ElementRef;
  regionsToSave: any[] = [];
  regionsToAdd: any[] = [];
  regions: any[] = [];
  tariffs: any[] = [];
  imgSrc: any = {};
  imageCropStep: number = 0;
  currentTariff: any;
  imageChangedEvent: any = '';
  croppedImage: any = '';

  constructor(private tariffsService: TariffsService) {
  }

  ngOnInit() {
    this.tariffsService.getAllTariffs().subscribe(res => {
      this.tariffs = res as any[];
    });

    this.tariffsService.getAllRegions().subscribe(res => {
      this.regions = res as any[];
      for (let i = 0; i < this.regions.length; i++) {
        this.regionsToAdd.push({
          id: '',
          price: 0,
          region: this.regions[i],
          tariff: {}
        });
      }
    }, err => {
      console.log(err)
    });
  }

  uploadPicture(): void {
    this.fileInput.nativeElement.click()
  }

  imageLoaded() {
  }

  toggle = function (item, list) {
    let idx = -1;
    for (let i = 0; i < list.length; i++) {
      if (list[i] == item)
        idx = i;
    }
    if (idx > -1) {
      list.splice(idx, 1);
    }
    else {
      list.push(item);
    }
  };

  exists = function (item, list) {
    if (list != undefined) {
      let idx = -1;
      for (let i = 0; i < list.length; i++) {
        if (list[i] == item)
          idx = i;
      }
      return idx > -1;
    }
    return false;
  };

  checkPrice = function (r, list) {
    if (r.price < 0) {
      r.price = 0;
    }
    if (r.price > 2000) {
      r.price = 2000;
    }
    for (let i = 0; i < list.length; i++) {
      if (list[i].region.id == r.region.id)
        list[i].price = r.price;
    }
  };

  fileChangeEvent(event: any): void {
    this.imageChangedEvent = event;
    this.imageCropStep = 2;
  }

  imageCropped(image: string) {
    this.croppedImage = image;
  }

  loadImageFailed() {
    // show message
  }

  crop() {
    this.imageCropStep = 3;
  }

  clear() {
    this.imageCropStep = 1;
    this.fileInput.nativeElement.value = '';
    delete this.imgSrc;
    this.imgSrc = {};
  }
}
