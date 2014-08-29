function tests = Phi3Test()
    tests = functiontests(localfunctions);
end


function drag = buildExampleDrag()
import endewem.*;

drag = newDrag();
drag.V = {'r', 'v1', 'v2', 'v3', 'v4'};
drag.r = 1;
drag.E = [[1 2]; [1 5]]; % the rest connect with the function.
drag = appendArc(drag, 'v1', 'v2');
drag = appendArc(drag, 'v1', 'v4');
drag.G = {'g1', 'g2', 'g3', 'g4'};
drag = map(drag, 'v1', 'g2');
drag = map(drag, 'v2', 'g2');
drag = map(drag, 'v4', 'g2');

end

function testTrivialDifferentiation(testCase)
import endewem.*;

drag = buildExampleDrag();
drag = map(drag, 'v2', 'g3');
drag.X = [1 3 4 1]; % some arbitrary gene expression levels

% X(g_2) == 3
expectedWeight = uint8(3); 
actualWeight = Phi3(drag, 'g2', 'v1');
testCase.verifyEqual(actualWeight, expectedWeight,...
    'Actual weight is different from expected.');

end

function testSubsetH(testCase)
import endewem.*;

drag = buildExampleDrag();
drag = map(drag, 'v2', 'g3');
drag.X = [1 3 4 1]; % some arbitrary gene expression levels

expectedWeight = uint16(3 + 4); 
H = find(ismember(drag.G, {'g2' 'g3'}));
actualWeight = PhiH(drag, @Phi3, H, 'v2');
testCase.verifyEqual(actualWeight, expectedWeight,...
    'Actual weight is different from expected.');
end
