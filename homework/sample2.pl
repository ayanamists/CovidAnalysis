insertAsc(country(Name,Case),[],[country(Name,Case)]).
insertAsc(country(Name,Case),[country(NameH,CaseH)|T],[country(Name,Case),country(NameH,CaseH)|T]):- Case =< CaseH, !.
insertAsc(country(Name,Case),[country(NameH,CaseH)|T1],[country(NameH,CaseH)|T2]):- insertAsc(country(Name,Case),T1,T2).


insortAsc([],[]).
insortAsc([country(NameH,CaseH)|T],Sorted):- insortAsc(T,Sorted2), insertAsc(country(NameH,CaseH),Sorted2,Sorted).
insertDesc(country(Name,Case),[],[country(Name,Case)]).
insertDesc(country(Name,Case),[country(NameH,CaseH)|T],[country(Name,Case),country(NameH,CaseH)|T]):- Case >= CaseH, !.
insertDesc(country(Name,Case),[country(NameH,CaseH)|T1],[country(NameH,CaseH)|T2]):- insertDesc(country(Name,Case),T1,T2).


insortDesc([],[]).
insortDesc([country(NameH,CaseH)|T],Sorted):- insortDesc(T,Sorted2), insertDesc(country(NameH,CaseH),Sorted2,Sorted).
