A = [0 1 1 0 0 0 0 ; 0 0 0 1 1 0 0 ; 0 0 0 0 0 1 1 ; 0 0 0 0 0 0 0 ; 0 0 0 0 0 0 0 ; 0 0 0 0 0 0 0 ; 0 0 0 0 0 0 0 ; ];
cla
subplot(1,1,1);
gplot(A,[0 1;0 2;2 2;0 3;1 3;2 3;3 3;],'-o');
text([0 0 2 0 1 2 3 ],[0.75 1.75 1.75 2.75 2.75 2.75 2.75 ],{'Mark','Steven','Eva','William','Alice','Jerry','Theresa',});
axis([-1 6 -1 5],'off')