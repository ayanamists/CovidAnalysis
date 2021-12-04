insert(country(Name,Case),[],[country(Name,Case)]).
insert(country(Name,Case),[country(NameH,CaseH)|T],[country(Name,Case),country(NameH,CaseH)|T]):- Case =< CaseH, !.
insert(country(Name,Case),[country(NameH,CaseH)|T1],[country(NameH,CaseH)|T2]):- insert(country(Name,Case),T1,T2).


insort([],[]).
insort([country(NameH,CaseH)|T],Sorted):- insort(T,Sorted2), insert(country(NameH,CaseH),Sorted2,Sorted).
insert(country(Name,Case),[],[country(Name,Case)]).
insert(country(Name,Case),[country(NameH,CaseH)|T],[country(Name,Case),country(NameH,CaseH)|T]):- Case >= CaseH, !.
insert(country(Name,Case),[country(NameH,CaseH)|T1],[country(NameH,CaseH)|T2]):- insert(country(Name,Case),T1,T2).


insort([],[]).
insort([country(NameH,CaseH)|T],Sorted):- insort(T,Sorted2), insert(country(NameH,CaseH),Sorted2,Sorted).
