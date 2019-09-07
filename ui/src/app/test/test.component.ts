import {Component, OnInit} from '@angular/core';
import 'rxjs/add/operator/finally';
import {WordsService} from "../shared/words.service";
import {LoaderService} from "../shared/loader.service";
import {AlertService} from "../shared/alert.service";
import {TranslatableWord} from "../words/words.component";
import {FormBuilder, FormGroup, FormGroupDirective, Validators} from "@angular/forms";
import {Subject} from "rxjs";

@Component({
  templateUrl: 'test.component.html',
  styleUrls: ['test.component.css']
})
export class TestComponent implements OnInit {

  private currentWordSubject = new Subject<TranslatableWord>();
  private currentWordObs = this.currentWordSubject.asObservable();

  private testStarted: boolean = false;
  private showHint: boolean = false;
  private translation: string = '';
  private wordIndex: number = 0;
  private allWords: TranslatableWord[] = [];
  private currentWord: TranslatableWord;

  translationInputForm: FormGroup;

  constructor(private wordsService: WordsService,
              private loaderService: LoaderService,
              private alertService: AlertService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.currentWordObs.subscribe(word => {
      this.currentWord = word;
    });
    this.createTranslationInputForm();
  }

  private createTranslationInputForm(): void {
    this.translationInputForm = this.formBuilder.group({
      translationInput: ['', Validators.required]
    });
  }

  private startTest(): void {
    this.wordsService.allUserWords().subscribe((words) => {
      this.allWords = words;
      if (this.allWords.length > 0) {
        this.currentWordSubject.next(this.allWords[this.wordIndex]);
        this.testStarted = true;
      } else {
        this.alertService.error("There are not words to test.");
      }
    })
  }

  private checkWord(formDirective: FormGroupDirective): void {
    let translations: string[] = this.currentWord.translations;
    let isTranslationCorrect = translations.includes(this.translation);
    if (isTranslationCorrect) {
      this.proceedToTheNextWord(formDirective, false);
    } else {
      this.alertService.error("Translation is not correct.");
    }
  }

  private proceedToTheNextWord(formDirective: FormGroupDirective, isSkip: boolean): void {
    this.resetForm(formDirective);
    this.doHideHint();
    if (!isSkip) {
      this.alertService.success("Correct");
    } else {
      this.alertService.success("Skipped");
    }
    this.wordIndex++;
    if (this.wordIndex < this.allWords.length) {
      this.currentWordSubject.next(this.allWords[this.wordIndex]);
    } else {
      this.wordIndex = 0;
      this.testStarted = false;
      if (!isSkip) {
        this.alertService.success("Answer is correct. Test is complete");
      } else {
        this.testStarted = false;
        this.resetForm(formDirective);
        this.alertService.success("Test is complete");
      }
    }
  }

  private resetForm(formDirective: FormGroupDirective): void {
    formDirective.resetForm();
    this.translationInputForm.reset();
    this.translation = '';
  }

  private nextWord(formDirective: FormGroupDirective): void {
    this.proceedToTheNextWord(formDirective, true);
  }

  private doShowHint(): void {
    let currentWord: TranslatableWord = this.currentWord;
    let fileLocation = currentWord.fileLocation;
    if (fileLocation) {
      this.showHint = true;
    } else {
      this.alertService.error("No image hint was defined for this word");
    }
  }

  private doHideHint(): void {
    this.showHint = false;
  }
}
