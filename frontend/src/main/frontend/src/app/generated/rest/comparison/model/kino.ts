/**
 * Api Documentation
 * Api Documentation
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import { Film } from './film';


/**
 * Kino
 */
export interface Kino { 
    /**
     * Kinoprogramm
     */
    filme?: Array<Film>;
    /**
     * Name des Kinos
     */
    kino?: string;
}
