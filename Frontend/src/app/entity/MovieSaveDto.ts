export interface MovieSaveDto{
    title: String;
    description: String;
    yearOfProduction: number;
    speciesId: number | undefined;
    movieUrl: String;
}