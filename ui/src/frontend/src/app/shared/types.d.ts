export interface Word {
  value: string;
}

export interface LoaderState {
  show: boolean;
}

export interface WordGroup {
  firstLetter: string;
  words: string[];
}
