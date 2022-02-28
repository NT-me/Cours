HEPTC=heptc
HEPTS=hepts
LESAR=lesar
MAIN=full_add
PERVASIVES:="$(shell $(HEPTC) -where)/c"

simulate: fulladder.epci fulladder
	$(HEPTS) -sig fulladder.epci -node $(MAIN) -exec ./fulladder

check: fulladder.lus
	$(LESAR) $< equivalence -diag

fulladder: fulladder_c/fulladder_types.c \
	   fulladder_c/fulladder.c \
	   fulladder_c/_main.c
	$(CC) -o $@ -I fulladder_c -I $(PERVASIVES) $^

fulladder.epci \
fulladder_c/fulladder.c \
fulladder_c/fulladder_types.c \
fulladder_c/_main.c: fulladder.lus
	heptc -s $(MAIN) -hepts -target c $<

clean:
	-@rm -rf fulladder_c Fulladder.obc
	-@rm -f fulladder *.epci *.log *.mls
